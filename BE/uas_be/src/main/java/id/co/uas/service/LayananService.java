package id.co.uas.service;

import id.co.uas.DtoResponse;
import id.co.uas.model.Layanan;

public interface LayananService {
    DtoResponse getAllLayanan();
    public DtoResponse getLayananId(int id);
    DtoResponse saveLayanan(Layanan Layanan);
    DtoResponse updateLayanan(Layanan Layanan);
    DtoResponse deleteLayanan(Layanan Layanan);
    DtoResponse deleteLayananById(int idLayanan);
}
