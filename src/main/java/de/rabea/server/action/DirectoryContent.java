package de.rabea.server.action;

import de.rabea.request.Directory;
import de.rabea.server.Action;

public class DirectoryContent implements Action {

    private Directory directory;

    public DirectoryContent(Directory directory) {
        this.directory = directory;
    }

    @Override
    public String response() {
        return listLinksToFiles();
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
