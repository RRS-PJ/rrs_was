package com.korit.projectrrs.dto.fileUpload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@Data
@AllArgsConstructor
public class GetFilePathAndName {
    String filePath;
    String fileName;

    public GetFilePathAndName(File file){
        this.filePath = file.getPath();
        this.fileName = file.getName();
    }
}
