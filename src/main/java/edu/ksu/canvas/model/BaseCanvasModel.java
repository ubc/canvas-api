package edu.ksu.canvas.model;

import java.lang.reflect.*;
import java.util.*;

import org.slf4j.*;

import com.google.gson.*;

import edu.ksu.canvas.annotation.*;
import edu.ksu.canvas.impl.GsonResponseParser;

public abstract class BaseCanvasModel {
    private static final Logger LOG = LoggerFactory.getLogger(BaseCanvasModel.class);

    /* Canvas has post parameter keys in non consistent formats. Occasionally they are 'class[field]' and other times
     * they are just 'field'. This method will create a map with the correct post keys and values based on the
     * @CanvasField and @CanvasObject annotations.
     */
    public Map<String, List<String>> toPostMap(boolean includeNulls) {
        Class<? extends BaseCanvasModel> clazz = this.getClass();
        Map<String, List<String>> postMap = new HashMap<>();
        for (Method method : clazz.getMethods()) {
            CanvasField canvasFieldAnnotation = method.getAnnotation(CanvasField.class);
            if (canvasFieldAnnotation != null && canvasFieldAnnotation.postKey() != null) {
                String postKey = getPostKey(canvasFieldAnnotation);
                try {
                    List<String> fieldValues = getFieldValues(method);
                    if (fieldValues != null && !fieldValues.isEmpty() || includeNulls) {
                        if (postMap.containsKey(postKey)) {
                            postMap.get(postKey).addAll(fieldValues);
                        } else {
                            postMap.put(postKey, fieldValues);
                        }
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    String message = "Could not access Canvas model getter for" + postKey;
                    LOG.error(message, e);
                    throw new IllegalStateException(message, e);
                }
            }
        }
        return postMap;
    }

    /**
     * Wraps a Canvas model inside of a JSON object so that the resulting serialized object
     * can be pushed to Canvas create/edit endpoints. For example, to create an assignment, the JSON
     * must look like: <pre>{assignment: {name: "Assignment 1"}}</pre>.
     * This method adds the outer "assignment" container based on CanvasObject notations on the model classes
     * @param serializeNulls Whether or not to include null fields in the serialized JSON. Defaults to false if null
     * @return A JsonObject suitable for serializing out to the Canvas API
     */
    public JsonObject toJsonObject(Boolean serializeNulls) {
        Class<? extends BaseCanvasModel> clazz = this.getClass();
        CanvasObject canvasObjectAnnotation = clazz.getAnnotation(CanvasObject.class);
        if(canvasObjectAnnotation == null || canvasObjectAnnotation.postKey() == null) {
            throw new IllegalArgumentException("Object to wrap must have a CanvasObject annotation with a postKey");
        }
        String objectPostKey = canvasObjectAnnotation.postKey();
        JsonElement element = GsonResponseParser.getDefaultGsonParser(serializeNulls).toJsonTree(this);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(objectPostKey, element);
        return jsonObject;
    }

    public JsonObject toJsonArrayObject(Boolean serializeNulls) {
        JsonElement element = GsonResponseParser.getDefaultGsonParser(serializeNulls).toJsonTree(this);
        return (JsonObject)element;
    }

    private String getPostKey(CanvasField canvasFieldAnnotation) {
        if (!canvasFieldAnnotation.array()) {
            return canvasFieldAnnotation.postKey();
        } else {
            return makeArrayPostKey(canvasFieldAnnotation);
        }
    }

    private String makeArrayPostKey(CanvasField canvasFieldAnnotation) {
        if (!canvasFieldAnnotation.overrideObjectKey().isEmpty()) {
            return canvasFieldAnnotation.overrideObjectKey() + "[" + canvasFieldAnnotation.postKey() + "]";
        }
        CanvasObject canvasObjectAnnotation = this.getClass().getAnnotation(CanvasObject.class);
        if (canvasObjectAnnotation == null || canvasObjectAnnotation.postKey() == null) {
            throw new IllegalArgumentException("CanvasObject does not contain postKey for " + this.getClass().getName());
        }
        return canvasObjectAnnotation.postKey() + "[" + canvasFieldAnnotation.postKey() + "]";
    }

    private List<String> getFieldValues(Method getter) throws InvocationTargetException, IllegalAccessException {
        List<String> fieldValues = new ArrayList<>(1);
        Class<?> returnType = getter.getReturnType();
        Object returnValue = getter.invoke(this);

        if (returnValue == null) {
           return Collections.emptyList();
        }

        if (Iterable.class.isAssignableFrom(returnType)) {
            for (Object value : (Iterable) returnValue) {
                fieldValues.add(String.valueOf(value));
            }
        }
        else {
            fieldValues.add(String.valueOf(returnValue));
        }
        return fieldValues;
    }
}
