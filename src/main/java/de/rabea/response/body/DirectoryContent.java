package de.rabea.response.body;

import de.rabea.request.Directory;
import de.rabea.server.ResponseBody;

public class DirectoryContent implements ResponseBody {

    private Directory directory;

    public DirectoryContent(Directory directory) {
        this.directory = directory;
    }

    @Override
    public byte[] response() {
        return listLinksToFiles().getBytes();
    }

    private String listLinksToFiles() {
        String links = "";
        for (String file : directory.contents) {
            links += "<a href=/" + fileName(file) + ">" + fileName(file) + "</a>";
        }
        return links;
    }

    private String fileName(String file) {
        String[] folders = file.split("/");
        return folders[folders.length - 1];
    }
}
