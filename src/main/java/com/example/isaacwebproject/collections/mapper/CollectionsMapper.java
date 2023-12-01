package com.example.isaacwebproject.collections.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.Optional;

@Mapper
public interface CollectionsMapper {
    Optional<Collection> getCollection(Integer id);
}
