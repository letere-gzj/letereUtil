package com.letere.bean;

import lombok.Data;

@Data
public class Location {
    /**
     * 国家
     */
    private String country;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 服务提供商
     */
    private String isp;

    public Location(String region) {
        // 模板：中国|0|广东省|广州市|电信
        String[] locations = region.split("\\|");
        this.country = "0".equals(locations[0]) ? null : locations[0];
        this.province = "0".equals(locations[2]) ? null :locations[2];
        this.city = "0".equals(locations[3]) ? null : locations[3];
        this.isp = "0".equals(locations[4]) ? null : locations[4];
    }
}
