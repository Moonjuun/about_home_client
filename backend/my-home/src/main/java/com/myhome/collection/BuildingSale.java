package com.myhome.collection;

import com.myhome.type.BuildingType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Document(collection = "building_sale")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildingSale {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private BuildingSale.request request;
    private BuildingSale.response response;

    @Builder
    public BuildingSale(BuildingSale.request request, BuildingSale.response response) {
        this.request = request;
        this.response = response;
    }

    @Builder
    public BuildingSale(BuildingSale.request request, List<BuildingSale.detail> detailList) {
        this.request = request;
        this.response = new BuildingSale.response(detailList);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class request {
        private String lawdCd; // 각 지역별 코드  11110
        private String dealYmd; // 월 단위 신고자료  201512
        private BuildingType buildingType;

        @Builder
        public request(String lawdCd, String dealYmd, BuildingType buildingType) {
            this.lawdCd = lawdCd;
            this.dealYmd = dealYmd;
            this.buildingType = buildingType;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class response{
        List<detail> list;

        @Builder
        public response(List<detail> list) {
            this.list = list;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class detail{
        private BigDecimal price;
        private String buildingName;
        private String year;
        private String month;
        private String day;
        private String streetCode;
        private String reginCode;
        private String streetName;

        @Builder
        public detail(BigDecimal price, String buildingName, String year, String month, String day, String streetCode, String reginCode, String streetName) {
            this.price = price;
            this.buildingName = buildingName;
            this.year = year;
            this.month = month;
            this.day = day;
            this.streetCode = streetCode;
            this.reginCode = reginCode;
            this.streetName = streetName;
        }
    }
}