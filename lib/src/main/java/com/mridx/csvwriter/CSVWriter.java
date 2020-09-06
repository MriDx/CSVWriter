package com.mridx.csvwriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CSVWriter {

    private FileOutputStream fileOutputStream;
    private File file;

    public static CSVWriter getInstance() {
        return new CSVWriter();
    }

    public void setFile(File file) throws FileNotFoundException {
        fileOutputStream = new FileOutputStream(file.getPath());
        this.file = file;
    }

    public void writeRow(String[] cols) throws IOException {
        if (fileOutputStream == null) return;
        StringBuilder builder = new StringBuilder();
        for (String s : cols) {
            builder.append(escape(s)).append(",");
        }
        fileOutputStream.write(builder.toString().getBytes());
    }

    private String escape(String s) {
        return s.replaceAll(",", " ");
    }

    public void writeRow(ArrayList<String> cols) throws IOException {
        if (fileOutputStream == null) return;
        StringBuilder builder = new StringBuilder();
        for (String s : cols) {
            builder.append(s).append(",");
        }
        fileOutputStream.write(builder.toString().getBytes());
    }

    public String saveWrittenData() throws IOException {
        if (fileOutputStream == null) return null;
        fileOutputStream.close();
        return file.getPath();
    }

}
