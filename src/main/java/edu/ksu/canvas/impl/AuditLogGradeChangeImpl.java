package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.AuditLogGradeChangeReader;
import edu.ksu.canvas.interfaces.CanvasWriter;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.model.audit.GradeChange;
import edu.ksu.canvas.model.audit.GradeChangeEvent;
import edu.ksu.canvas.model.audit.GradeChangeLinked;
import edu.ksu.canvas.model.audit.GradeChangeLinks;
import edu.ksu.canvas.model.audit.GradeChangeWrapper;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.ListGradeChangeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuditLogGradeChangeImpl extends BaseImpl<GradeChange, AuditLogGradeChangeReader, CanvasWriter> implements AuditLogGradeChangeReader {
    private static final Logger LOG = LoggerFactory.getLogger(AuditLogGradeChangeReader.class);

    public AuditLogGradeChangeImpl(final String canvasBaseUrl, final Integer apiVersion, final OauthToken oauthToken, final RestClient restClient, final int connectTimeout, final int readTimeout, final Integer paginationPageSize, final Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
    }

    @Override
    public List<GradeChange> listGradeChanges(final ListGradeChangeOptions options) throws IOException {
        LOG.debug("Retrieving grade changes for {}", options.getDescription());
        final String url = buildCanvasUrl("audit/grade_change", options.getOptionsMap());
        final List<Response> response = canvasMessenger.getFromCanvas(oauthToken, url);
        return response.stream()
                .map(r -> GsonResponseParser.getDefaultGsonParser(serializeNulls)
                        .fromJson(r.getContent(), GradeChangeWrapper.class))
                .flatMap(wrapper -> parseGradeChangeWrapper(wrapper).stream())
                .collect(Collectors.toList());
    }

    private List<GradeChange> parseGradeChangeWrapper(final GradeChangeWrapper wrapper) {
        final List<GradeChange> changes = new ArrayList<>();
        for (final GradeChangeEvent event : wrapper.getEvents()) {
            final GradeChange change = new GradeChange();
            change.setId(event.getId());
            change.setCreatedAt(event.getCreatedAt());
            change.setEventType(event.getEventType());
            change.setGradeBefore(event.getGradeBefore());
            change.setGradeAfter(event.getGradeAfter());

            final GradeChangeLinks links = event.getLinks();
            final GradeChangeLinked linked = wrapper.getLinked();
            if (links != null && linked != null) {
                change.setGrader(findLinkedUser(links.getGrader(), linked));
                change.setStudent(findLinkedUser(links.getStudent(), linked));
            }
            changes.add(change);
        }
        return changes;
    }

    private User findLinkedUser(final String id, final GradeChangeLinked linked) {
        if (id == null) {
            return null;
        }
        final int intId = Integer.parseInt(id);
        return linked.getUsers().stream()
                .filter(user -> user.getId() == intId)
                .findFirst()
                .orElse(null);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<GradeChange>>() {}.getType();
    }

    @Override
    protected Class<GradeChange> objectType() {
        return GradeChange.class;
    }

}
