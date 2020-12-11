package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.AuditLogGradeChangeReader;
import edu.ksu.canvas.interfaces.CanvasWriter;
import edu.ksu.canvas.model.audit.GradeChange;
import edu.ksu.canvas.model.audit.GradeChangeWrapper;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.ListGradeChangeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
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
                .flatMap(wrapper -> wrapper.getEvents().stream())
                .collect(Collectors.toList());
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
