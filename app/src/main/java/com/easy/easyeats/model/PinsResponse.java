package com.easy.easyeats.model;

import java.util.List;
import java.util.Objects;

public class PinsResponse {
    public String total;
    public List<Pin> results;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PinsResponse that = (PinsResponse) o;
        return Objects.equals(total, that.total) && Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, results);
    }

    @Override
    public String toString() {
        return "PinsResponse{" +
                "total='" + total + '\'' +
                ", results=" + results +
                '}';
    }
}
