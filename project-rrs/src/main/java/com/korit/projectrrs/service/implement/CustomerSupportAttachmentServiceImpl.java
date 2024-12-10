//package com.korit.projectrrs.service.implement;
//
//import com.korit.projectrrs.common.ResponseMessage;
//import com.korit.projectrrs.dto.customerSupportAttachment.response.CustomerSupportAttachmentResponseDto;
//import com.korit.projectrrs.entity.CustomerSupport;
//import com.korit.projectrrs.entity.CustomerSupportAttachment;
//import com.korit.projectrrs.repositoiry.CustomerSupportAttachmentRepository;
//import com.korit.projectrrs.repositoiry.CustomerSupportRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class CustomerSupportAttachmentServiceImpl implements CustomerSupportAttachmentService {
//
//    private final CustomerSupportRepository customerSupportRepository;
//    private final CustomerSupportAttachmentRepository customerSupportAttachmentRepository;
//
//    @Value("${file.upload-dir}")
//    private String uploadDir;
//
//    @Override
//    public ResponseDto<List<CustomerSupportAttachmentResponseDto>> saveAttachments(List<MultipartFile> files, Long customerSupportId) {
//        List<CustomerSupportAttachment> attachments = new ArrayList<>();
//
//        Optional<CustomerSupport> optionalCustomerSupport = customerSupportRepository.findById(customerSupportId);
//        if (optionalCustomerSupport.isEmpty()) {
//            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CUSTOMER_SUPPORT);
//        }
//
//        CustomerSupport customerSupport = optionalCustomerSupport.get();
//
//        File directory = new File(uploadDir);
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//
//        try {
//            for (MultipartFile file : files) {
//                String extension = getFileExtension(file.getOriginalFilename());
//                if (!"jpg".equals(extension) && !"png".equals(extension)) {
//                    return ResponseDto.setFailed(ResponseMessage.BAD_REQUEST);
//                }
//
//                String fileName = UUID.randomUUID() + "." + extension;
//                File saveFile = new File(directory, fileName);
//                file.transferTo(saveFile);
//
//                CustomerSupportAttachment attachment = CustomerSupportAttachment.builder()
//                        .customerSupport(customerSupport)
//                        .customerSupportAttachmentFile(uploadDir + "/" + fileName)
//                        .build();
//
//                customerSupportAttachmentRepository.save(attachment);
//                attachments.add(attachment);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed(ResponseMessage.FILE_UPLOAD_ERROR);
//        }
//
//        List<CustomerSupportAttachmentResponseDto> attachmentDtos = attachments.stream()
//                .map(attachment -> CustomerSupportAttachmentResponseDto.builder()
//                        .customerSupportAttachmentId(attachment.getCustomerSupportAttachmentId())
//                        .customerSupportAttachmentFile(attachment.getCustomerSupportAttachmentFile())
//                        .build())
//                .collect(Collectors.toList());
//
//        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, attachmentDtos);
//    }
//
//    @Override
//    public ResponseDto<Void> deleteAttachments(Long customerSupportAttachmentId) {
//        try {
//            Optional<CustomerSupportAttachment> optionalAttachment =
//                    customerSupportAttachmentRepository.findById(customerSupportAttachmentId);
//
//            if (optionalAttachment.isEmpty()) {
//                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CUSTOMER_SUPPORT_ATTACHMENT);
//            }
//
//            CustomerSupportAttachment attachment = optionalAttachment.get();
//            File file = new File(attachment.getCustomerSupportAttachmentFile());
//            if (file.exists()) {
//                file.delete();
//            }
//
//            customerSupportAttachmentRepository.delete(attachment);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
//        }
//
//        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
//    }
//
//    private String getFileExtension(String fileName) {
//        if (fileName == null || !fileName.contains(".")) {
//            return "";
//        }
//        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//    }
//}
