package com.example.safetynet.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.model.Person;

public class AllChildByAddressDTO {

    private List<ChildByAddressDTO> listChildByAddressDTO;
    private List<Person> houseMember;

    public AllChildByAddressDTO(List<ChildByAddressDTO> listChildByAddressDTO, List<Person> houseMember) {
        this.listChildByAddressDTO = listChildByAddressDTO;
        this.houseMember = houseMember;
    }

    public AllChildByAddressDTO() {
        this.listChildByAddressDTO = new ArrayList<>();
        this.houseMember = new ArrayList<>();
    }

    public List<ChildByAddressDTO> getListChildByAddressDTO() {
        return listChildByAddressDTO;
    }

    public void setListChildByAddressDTO(List<ChildByAddressDTO> listChildByAddressDTO) {
        this.listChildByAddressDTO = listChildByAddressDTO;
    }

    public List<Person> getHouseMember() {
        return houseMember;
    }

    public void setHouseMember(List<Person> houseMember) {
        this.houseMember = houseMember;
    }

}
