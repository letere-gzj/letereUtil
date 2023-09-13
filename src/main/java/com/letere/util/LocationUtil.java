package com.letere.util;

import lombok.Data;
import org.lionsoul.ip2region.xdb.Searcher;

public class LocationUtil {

    /**
     * 地球半经（千米）
     */
    private final static Double EARTH_RADIUS = 6371.393;

    /**
     * ip2region.db文件位置
     */
    private static final String DB_RELATIVE_PATH = "src/main/resources/ip2region/ip2region.xdb";

    /**
     * 缓存
     */
    private static byte[] vectorIndex = null;
    static {
        // 将vectorIndex预先加载
        try {
            vectorIndex = Searcher.loadVectorIndexFromFile(DB_RELATIVE_PATH);
        } catch (Exception e) {
            System.out.printf("failed to load vector index from `%s`: %s\n", DB_RELATIVE_PATH, e);
        }
    }

    @Data
    public static class Location {
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

    /**
     * 通过ip地址，寻找对应的省，市位置
     * @param ip ip地址
     * @return ip所属位置
     */
    public static Location getLocationFromIp(String ip) {
        Location location = null;
        try {
            Searcher searcher = Searcher.newWithVectorIndex(DB_RELATIVE_PATH, vectorIndex);
            // 查询并封装为Location
            String search = searcher.search(ip);
            if (search != null && search.trim().length() > 0) {
                location = new Location(search);
            }
            searcher.close();
        } catch (Exception e) {
            System.out.printf("failed to find location from ip[%s]\n", ip);
        }
        return location;
    }

    /**
     * 经纬度距离计算
     * @param lonA A点经度
     * @param latA A点纬度
     * @param lonB B点经度
     * @param latB B点纬度
     * @return 单位（米）
     */
    public static Double getGeoDistance(Double lonA, Double latA, Double lonB, Double latB){
        // A点
        Double jA = Math.toRadians(lonA);
        Double wA = Math.toRadians(latA);
        // B点
        Double jB = Math.toRadians(lonB);
        Double wB = Math.toRadians(latB);
        //使用公式进行计算距离
        return EARTH_RADIUS * Math.acos(Math.cos(wA) * Math.cos(wB) * Math.cos(jB - jA) + Math.sin(wA) * Math.sin(wB)) * 1000;
    }
}
