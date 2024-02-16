package com.ldbbank.hrrecruitmentapi.services.applicantInfo;

import com.ldbbank.hrrecruitmentapi.entity.ApplicantInfo;
import com.ldbbank.hrrecruitmentapi.payload.request.ApplicantInfoRequest;
import com.ldbbank.hrrecruitmentapi.payload.request.RegisterRequest;
import com.ldbbank.hrrecruitmentapi.payload.response.ResponseMessage;
import com.ldbbank.hrrecruitmentapi.repository.ApplicantInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@Slf4j
public class ApplicantInfoServiceImpl implements ApplicantInfoService {
    private final ApplicantInfoRepository applicantInfoRepository;

    public ApplicantInfoServiceImpl(ApplicantInfoRepository applicantInfoRepository) {
        this.applicantInfoRepository = applicantInfoRepository;
    }

    @Override
    public ResponseMessage submitApplicantInfo(ApplicantInfoRequest request) {

        ResponseMessage responseMessage = new ResponseMessage();
        MultipartFile video = request.getVideo();
        MultipartFile cv = request.getCv();
        int tenMB = 10 * 1024 * 1024; // 10MB in bytes
        byte[] bytes = new byte[tenMB]; // 100MB
        int hundredMB = 100 * 1024 * 1024; // 10MB in bytes
        byte[] bytesVideo = new byte[hundredMB]; // 100MB

        if (cv.isEmpty() || cv.getSize() > bytes.length ) {
            responseMessage.setMessage(cvUpload(cv));
            return responseMessage;
        }
        if (video.isEmpty() || video.getSize() > bytesVideo.length) {
            responseMessage.setMessage(videoUpload(video));
            return responseMessage;
        }

        try {
            ApplicantInfo res = new ApplicantInfo();

            res.setName(request.getName());
            res.setGender(request.getGender());
            res.setInterest_position(request.getInterest_position());
            res.setLocation_based(request.getLocation_based());
            res.setEducation(request.getEducation());
            res.setMajoring(request.getMajoring());
            res.setWork_exp(request.getWork_exp());
            res.setTell(request.getTell());
            res.setEmail(request.getEmail());
            res.setLanguage(request.getLanguage());

            // Get the video file and save it somewhere
            res.setVideo(videoUpload(video));

            // Get the CV file and save it somewhere
            res.setCv(cvUpload(cv));
            applicantInfoRepository.save(res);
            responseMessage.setStatus(true);
            responseMessage.setDataResponse(res);
            responseMessage.setMessage("OK");


        } catch (Exception e) {
            System.out.println("eee" + e);
            responseMessage.setMessage("EXCEPTION" + e.getMessage());
        }
        return responseMessage;
    }

    @Override
    public ResponseMessage getApplicantInfo() {
        ResponseMessage responseMessage = new ResponseMessage();
        try {

            Iterable<ApplicantInfo> applicantInfos = applicantInfoRepository.findAll();

            responseMessage.setStatus(true);
            responseMessage.setDataResponse(applicantInfos);
            responseMessage.setMessage("OK");

        } catch (Exception e) {
            System.out.println("eee" + e);
            responseMessage.setMessage("EXCEPTION" + e.getMessage());
        }

        return responseMessage;
    }

    @Override
    public ResponseMessage getApplicantInfoById(Long id) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {

            Optional<ApplicantInfo> applicantInfos = applicantInfoRepository.findById(id);

            responseMessage.setStatus(true);
            responseMessage.setDataResponse(applicantInfos);
            responseMessage.setMessage("OK");

        } catch (Exception e) {
            System.out.println("eee" + e);
            responseMessage.setMessage("EXCEPTION" + e.getMessage());
        }

        return responseMessage;
    }

    @Override
    public ResponseMessage updateApplicantInfoUpdateById(Long id, ApplicantInfoRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {

            Optional<ApplicantInfo> res = applicantInfoRepository.findById(id);

            if (res.isPresent()) {
                ApplicantInfo getInfo = res.get();
                getInfo.setName(request.getName());
                getInfo.setGender(request.getGender());
                getInfo.setInterest_position(request.getInterest_position());
                getInfo.setLocation_based(request.getLocation_based());
                getInfo.setEducation(request.getEducation());
                getInfo.setMajoring(request.getMajoring());
                getInfo.setWork_exp(request.getWork_exp());
                getInfo.setTell(request.getTell());
                getInfo.setEmail(request.getEmail());
                getInfo.setLanguage(request.getLanguage());
//                getInfo.setVideo(request.getVideo());
//                getInfo.setCv(request.getCv());
                applicantInfoRepository.save(getInfo);

                responseMessage.setStatus(true);
                responseMessage.setDataResponse(res);
                responseMessage.setMessage("Update Successfully");
            } else {
                responseMessage.setStatus(false);
                responseMessage.setDataResponse("Data not found");
                responseMessage.setMessage("empty");

            }


        } catch (Exception e) {
            System.out.println("eee" + e);
            responseMessage.setMessage("EXCEPTION" + e.getMessage());
        }
        return responseMessage;
    }

    @Override
    public ResponseMessage deleteApplicantInfoById(Long id) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {

            Optional<ApplicantInfo> res = applicantInfoRepository.findById(id);

            if (res.isPresent()) {
                applicantInfoRepository.delete(res.get());

                responseMessage.setStatus(true);
                responseMessage.setDataResponse(res);
                responseMessage.setMessage("Delete successfully");
            } else {
                responseMessage.setStatus(false);
                responseMessage.setDataResponse("Data not found");
                responseMessage.setMessage("empty");

            }


        } catch (Exception e) {
            System.out.println("eee" + e);
            responseMessage.setMessage("EXCEPTION" + e.getMessage());
        }
        return responseMessage;
    }


    //    video upload
    public String videoUpload(MultipartFile file) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String fileName = file.getOriginalFilename();
        File newFile = new File("./files/video/" + fileName);
        int MB = 100 * 1024 * 1024; // 10MB in bytes
        byte[] bytes = new byte[MB]; // 100MB

        if (file.isEmpty()) {
            return "Please select a Video to upload.";
        }
        if (file.getSize() > bytes.length) {
            return "Video size exceeds the limit 100MB.";
        }

        try{
            inputStream = file.getInputStream();

            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;


            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            System.out.println("error" + e);
            e.printStackTrace();
        }
        return newFile.getName();
    }

    //    CV upload
    public String cvUpload(MultipartFile file) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String fileName = file.getOriginalFilename();
        File newFile = new File("./files/CV/" + fileName);
        int tenMB = 10 * 1024 * 1024; // 10MB in bytes
        byte[] bytes = new byte[tenMB]; // 10MB

        if (file.isEmpty()) {
            return "Please select a CV file to upload.";
        }
        if (file.getSize() > bytes.length) {
            return "File CV size exceeds the limit 10MB.";
        }

        try {
            inputStream = file.getInputStream();

            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;


            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            System.out.println("error" + e);
            e.printStackTrace();
        }

        return newFile.getName();
//        return newFile.getAbsolutePath();
    }

}
