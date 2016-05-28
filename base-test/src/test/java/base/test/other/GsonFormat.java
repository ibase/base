package java.base.test.other;

/**
 * Created by base on 2016/5/23.
 */
public class GsonFormat {


    /**
     * touser : OPENID
     * template_id : ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY
     * url : http://weixin.qq.com/download
     * data : {"first":{"value":"恭喜你购买成功！","color":"#173177"},"keynote1":{"value":"巧克力","color":"#173177"},"keynote2":{"value":"39.8元","color":"#173177"},"keynote3":{"value":"2014年9月22日","color":"#173177"},"remark":{"value":"欢迎再次购买！","color":"#173177"}}
     */

    private String touser;
    private String template_id;
    private String url;
    /**
     * first : {"value":"恭喜你购买成功！","color":"#173177"}
     * keynote1 : {"value":"巧克力","color":"#173177"}
     * keynote2 : {"value":"39.8元","color":"#173177"}
     * keynote3 : {"value":"2014年9月22日","color":"#173177"}
     * remark : {"value":"欢迎再次购买！","color":"#173177"}
     */

    private DataBean data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * value : 恭喜你购买成功！
         * color : #173177
         */

        private FirstBean first;
        /**
         * value : 巧克力
         * color : #173177
         */

        private Keynote1Bean keynote1;
        /**
         * value : 39.8元
         * color : #173177
         */

        private Keynote2Bean keynote2;
        /**
         * value : 2014年9月22日
         * color : #173177
         */

        private Keynote3Bean keynote3;
        /**
         * value : 欢迎再次购买！
         * color : #173177
         */

        private RemarkBean remark;

        public FirstBean getFirst() {
            return first;
        }

        public void setFirst(FirstBean first) {
            this.first = first;
        }

        public Keynote1Bean getKeynote1() {
            return keynote1;
        }

        public void setKeynote1(Keynote1Bean keynote1) {
            this.keynote1 = keynote1;
        }

        public Keynote2Bean getKeynote2() {
            return keynote2;
        }

        public void setKeynote2(Keynote2Bean keynote2) {
            this.keynote2 = keynote2;
        }

        public Keynote3Bean getKeynote3() {
            return keynote3;
        }

        public void setKeynote3(Keynote3Bean keynote3) {
            this.keynote3 = keynote3;
        }

        public RemarkBean getRemark() {
            return remark;
        }

        public void setRemark(RemarkBean remark) {
            this.remark = remark;
        }

        public static class FirstBean {
            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class Keynote1Bean {
            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class Keynote2Bean {
            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class Keynote3Bean {
            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class RemarkBean {
            private String value;
            private String color;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }
    }
}
