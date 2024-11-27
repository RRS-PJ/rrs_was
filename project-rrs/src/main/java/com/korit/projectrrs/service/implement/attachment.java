//package com.korit.projectrrs.service.implement;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class attachment {
//
//    private final CommunityRepository communityRepository;
//    private final UserRepository userRepository;
//
//    private List<CommunityAttachment> saveAttachments(List<MultipartFile> files, Community community) throws IOException {
//        List<CommunityAttachment> attachments = new ArrayList<>();
//        if (files != null && !files.isEmpty()) {
//            String uploadDir = getUploadDir();
//            File directory = new File(uploadDir);
//            if (!directory.exists()) {
//                directory.mkdirs(); // 디렉토리가 없으면 생성
//            }
//
//            for (MultipartFile file : files) {
//                String extension = getFileExtension(file.getOriginalFilename());
//                if (!"jpg".equals(extension) && !"png".equals(extension)) {
//                    throw new IllegalArgumentException("Only JPG and PNG files are allowed");
//                }
//
//                String fileName = UUID.randomUUID() + "." + extension;
//                File saveFile = new File(directory, fileName);
//                try {
//                    file.transferTo(saveFile);
//                } catch (Exception e) {
//                    saveFile.delete(); // 업로드 실패 시 파일 삭제
//                    throw e;
//                }
//
//                CommunityAttachment attachment = CommunityAttachment.builder()
//                        .community(community)
//                        .communityAttachmentImageFile("/uploads/" + fileName)
//                        .build();
//
//                attachments.add(attachment);
//            }
//        }
//        return attachments;
//    }
//
//    private String getFileExtension(String fileName) {
//        if (fileName == null || !fileName.contains(".")) {
//            return "";
//        }
//        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//    }
//}