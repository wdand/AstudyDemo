package com.bingkong.bkbase.model;

public class ShipAddressHintInfo {
    public static class HintInfoBean {
        public String fieldName;
        public String hintFocus;
        public String hintUnFocus;

        public HintInfoBean(String fieldName, String hintFocus, String hintUnFocus) {
            this.fieldName = fieldName;
            this.hintFocus = hintFocus;
            this.hintUnFocus = hintUnFocus;
        }
    }

    public HintInfoBean country;
    public HintInfoBean addressLine1;
    public HintInfoBean addressLine2;
    public HintInfoBean city;
    public HintInfoBean state;
    public HintInfoBean zipcode;

    public ShipAddressHintInfo() {
        setDefaultValue();
    }

    public ShipAddressHintInfo(String countryCode) {
        this.country = new HintInfoBean("country",
                "Country",
                "Country");
        if (countryCode.equals("US")) {
            this.addressLine1 = new HintInfoBean("addressLine1",
                    "4563 Studio City",
                    "Address Line 1");
            this.addressLine2 = new HintInfoBean("addressLine2",
                    "xxx company",
                    "Address Line 2");
            this.city = new HintInfoBean("city",
                    "Hollywood",
                    "City");
            this.state = new HintInfoBean("state",
                    "CA",
                    "State");
            this.zipcode = new HintInfoBean("zipcode",
                    "90321",
                    "Zip Code");
        } else if (countryCode.equals("DE")) {
            /*
             * Siemens AG
             *   Werner-von-Siemens-Straße 1
             *   80333 Munich
                 Germany
                 */
            this.addressLine1 = new HintInfoBean("addressLine1",
                    "Werner-von-Siemens-Straße 1",
                    "Address Line 1");
            this.addressLine2 = new HintInfoBean("addressLine2",
                    "xxx Company",
                    "Address Line 2");
            this.city = new HintInfoBean("city",
                    "Munich",
                    "City");
            this.state = new HintInfoBean("state",
                    "",
                    "");
            this.zipcode = new HintInfoBean("zipcode",
                    "80333",
                    "Zip Code");
        } else if (countryCode.equals("CN")) {
            this.addressLine1 = new HintInfoBean("addressLine1",
                    "No. 1 Century Avenue",
                    "Address Line 1");
            this.addressLine2 = new HintInfoBean("addressLine2",
                    "Pu Dong New Area",
                    "District");
            this.city = new HintInfoBean("city",
                    "Shanghai",
                    "City");
            this.state = new HintInfoBean("state",
                    "Shanghai",
                    "Province");
            this.zipcode = new HintInfoBean("zipcode",
                    "200000",
                    "Postal Code");
        } else if (countryCode.equals("UK")) {
            this.addressLine1 = new HintInfoBean("addressLine1",
                    "British Museum",
                    "Address Line 1");
            this.addressLine2 = new HintInfoBean("addressLine2",
                    "Great Russell Street",
                    "Address Line 2");
            this.city = new HintInfoBean("city",
                    "London",
                    "Dependent Locality");
            this.state = new HintInfoBean("state",
                    "London",
                    "City/Town/Locality");
            this.zipcode = new HintInfoBean("zipcode",
                    "WC1B 3DG",
                    "Postal Code");
        } else if (countryCode.equals("IN")) {
            this.addressLine1 = new HintInfoBean("addressLine1",
                    "National Museum",
                    "Address Line 1");
            this.addressLine2 = new HintInfoBean("addressLine2",
                    "Janpath",
                    "Address Line 2");
            this.city = new HintInfoBean("city",
                    "New Delhi",
                    "Dependent Locality");
            this.state = new HintInfoBean("state",
                    "New Delhi",
                    "City/Town/Locality");
            this.zipcode = new HintInfoBean("zipcode",
                    "110 011",
                    "Postal Code");
        } else {
            setDefaultValue();
        }
    }

    private void setDefaultValue() {
        this.addressLine1 = new HintInfoBean("addressLine1",
                "Address Line 1",
                "Address Line 1");
        this.addressLine2 = new HintInfoBean("addressLine2",
                "Address Line 2",
                "Address Line 2");
        this.city = new HintInfoBean("city",
                "City",
                "City");
        this.state = new HintInfoBean("state",
                "State",
                "State");
        this.zipcode = new HintInfoBean("zipcode",
                "Postal code",
                "Postal code");
    }
}
