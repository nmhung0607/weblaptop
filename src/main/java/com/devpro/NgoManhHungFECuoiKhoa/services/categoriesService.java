package com.devpro.NgoManhHungFECuoiKhoa.services;

import org.springframework.stereotype.Service;

import com.devpro.NgoManhHungFECuoiKhoa.model.Categories;
@Service
public class categoriesService extends BaseService<Categories> {
	
        @Override
		protected Class<Categories> clazz() {
			return Categories.class;
		}
}
