package com.sysuser.model.common;


import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tree {
	private String id;
	private String text;
	private List<Tree> children;
}
