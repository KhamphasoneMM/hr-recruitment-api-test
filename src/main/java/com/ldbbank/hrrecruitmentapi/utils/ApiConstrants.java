package com.ldbbank.hrrecruitmentapi.utils;

public class ApiConstrants {
    public static final String API_VERSION = "/api/v1";
    public static final class AUTH {
        public static final String MAIN_PATH = "/auth";
        public static final String REGISTER = "/register";
        public static final String LOGIN = "/login";
        public static final String USER_DETAILS = "/user-detail";

    }
    public static final class APPLICANT_INFO {
        public static final String APPLICANT_INFO_SUBMIT = "/applicant_submit";
        public static final String APPLICANT_INFO_ALL = "/applicant_info";

        public static final String APPLICANT_INFO_BY_ID = "/applicant_info/{id}";
        public static final String APPLICANT_INFO_UPDATE = "/applicant_info/update/{id}";
        public static final String APPLICANT_INFO_DELETE = "/applicant_info/delete/{id}";
    }
}
