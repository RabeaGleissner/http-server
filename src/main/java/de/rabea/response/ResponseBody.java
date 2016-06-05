package de.rabea.response;

import de.rabea.request.FileParser;
import de.rabea.request.Request;
import de.rabea.server.Resource;

import java.util.List;

public class ResponseBody {

    private String receivedMessage;
    private String directory;
    private Request request;
    Resource resource;

    public ResponseBody(Request request, String directory) {
        this.directory = directory;
        this.receivedMessage = receivedMessage(request);
        this.resource = new Resource();
        this.request = request;
    }

    public String receivedMessage(Request request) {
        if (!request.hasBody()) {
            return request.body2();
        }
        if (!request.hasUrlParams()) {
            return request.urlParams();
        }
        return "";
    }

    public byte[] create() {
        if (!receivedMessage.equals("")) {
            return receivedMessage.getBytes();
        }

        if (directoryHasContent()) {
            String url = request.url();
            List<String> list = resource.directoryContents(directory);

            if (resource.directoryContents(directory).contains(directory + resource.file(request.route()))) {
                byte[] fileContent;
                if (request.isPartial()) {
                    fileContent = new FileParser(directory + request.route(), request.range()).read();
                } else {
                    fileContent = new FileParser(directory + request.route()).read();
                }
                return fileContent;
            }

            if (showDirectoryContents()) {
                String urlsToFiles = "";
                for (String file : resource.directoryContents(directory)) {
                    urlsToFiles += "<a href=/" + fileName(file) + ">" + fileName(file) + "</a>";
                }
                return urlsToFiles.getBytes();
            }

        }
        return new byte[0];
    }

    private boolean directoryHasContent() {
        return resource.isDirectory(directory);
    }

    private String fileName(String file) {
        String[] folders = file.split("/");
        return folders[folders.length - 1];
    }

    private boolean showDirectoryContents() {
        return resource.requestRoot(request.route()) && resource.directoryHasContent(directory);
    }
}
