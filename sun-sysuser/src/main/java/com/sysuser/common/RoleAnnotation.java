package com.sysuser.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Documented
@Target(ElementType.METHOD)//作用在方法
@Retention(RetentionPolicy.RUNTIME)//仅在运行时RetentionPolicy
public @interface RoleAnnotation{

	/**
	 * <b>“且”关系的Role</b>
	 * <p>
	 * 必须指定type
	 * </p>
	 */
	public static String TYPE_AND = "and";
	/**
	 * <b>“或”关系的Role</b>
	 */
	public static String TYPE_OR = "or";
	/**
	 * <b>“混合”关系的Role</b>
	 * <p>
	 * 必须指定type
	 * </p>
	 */
	public static String TYPE_MIXED = "mixed";

	/**
	 * <b>Role之间的关联方式</b>
	 */
	String type() default RoleAnnotation.TYPE_OR;

	/**
	 * <b>“且”关系的Role</b>
	 * <p>
	 * 必须指定type
	 * </p>
	 */
	String[] and() default {};

	/**
	 * <b>“或”关系的Role</b>
	 */
	@AliasFor("value")
	String[] or() default {};

	@AliasFor("or")
	String[] value() default {};

	/**
	 * <b>“混合”关系的Role</b>
	 * <p>
	 * 必须指定type
	 * </p>
	 */
	String mixed() default ""; // ( (hr && fe) || admin )

}
