package com.bingkong.bkbase.model;

import java.util.List;

public class CatagoryModel {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : b7b6a01e-5f25-11e8-b304-8d5b1e833a99
         * name : Men
         * subCategories : [{"id":"cf9a4fd7-5fe9-11e8-bc30-00163e085727","name":"Sweater"},{"id":"ede4753a-5f25-11e8-b304-8d5b1e833a99","name":"T-shirt"},{"id":"ede4753a-5f25-11e8-b305-8d5b1e833a99","name":"LongSleeve"},{"id":"ede4753a-5f25-11e8-b307-8d5b1e833a99","name":"ShortSleeve"}]
         */

        private String id;
        private String name;
        private List<SubCategoriesBean> subCategories;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SubCategoriesBean> getSubCategories() {
            return subCategories;
        }

        public void setSubCategories(List<SubCategoriesBean> subCategories) {
            this.subCategories = subCategories;
        }

        public static class SubCategoriesBean {
            /**
             * id : cf9a4fd7-5fe9-11e8-bc30-00163e085727
             * name : Sweater
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
