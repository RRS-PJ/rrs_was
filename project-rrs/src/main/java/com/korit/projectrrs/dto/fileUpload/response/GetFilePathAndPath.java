package com.korit.projectrrs.dto.fileUpload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetFilePathAndPath {
    String filePath;
    String fileName;
}
