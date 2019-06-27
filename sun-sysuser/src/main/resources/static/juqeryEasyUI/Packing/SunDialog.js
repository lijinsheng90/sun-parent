/**
 * jquery easy ui dialog 封装
 */

/**
 * 
 * @param dialogid
 *            dialog挂载点
 */

function SunDialog(dialogid,title) {
	this.beanDialog = this;
	this.dialogid = dialogid; // grid 挂载点
	this.title=title;
	this.width=650;
	this.height=400;
	this.collapsible=true;//定义是否显示折叠按钮。
	this.minimizable=false;//是否显示最小化按钮
	this.maximizable=true;//是否显示最大化按钮
	this.resizable=true;//对话框是否可调整尺寸
	this.closed=false;//
	this.cache=false;
	this.modal=true;
	this.iconCls=null;//图标
	this.toolbar=null;//顶部工具栏
	this.buttons=null;//底部工具栏
};

SunDialog.prototype.setWidth = function(width) {
	this.width = width;
};

SunDialog.prototype.setHeight = function(height) {
	this.height = height;
};

SunDialog.prototype.setCollapsible = function(collapsible) {
	this.collapsible = collapsible;
};

SunDialog.prototype.setMaximizable = function(maximizable) {
	this.maximizable = maximizable;
};

SunDialog.prototype.setResizable = function(resizable) {
	this.resizable = resizable;
};

SunDialog.prototype.setIconCls = function(iconCls) {
	this.iconCls = iconCls;
};

SunDialog.prototype.setToolbar = function(toolbar) {
	this.toolbar = toolbar;
};

SunDialog.prototype.setButtons = function(buttons) {
	this.buttons = buttons;
};

SunDialog.prototype.build = function() {
	this.beanDialog = jQuery('#' + this.dialogid).dialog({
		    title:this.title,
		    width:this.width,
		    height:this.height,
		    collapsible:this.collapsible,//定义是否显示折叠按钮。
		    minimizable:false,//是否显示最小化按钮
		    maximizable:this.maximizable,//是否显示最大化按钮
		    resizable:this.resizable,//对话框是否可调整尺寸
		    closed: false,
		    cache: false,
		    modal: true,
		    iconCls:this.iconCls,
		    toolbar:this.toolbar,
			buttons:this.buttons
	});
};

SunDialog.prototype.close = function() {
	jQuery('#' + this.dialogid).dialog("close");
};

SunDialog.prototype.open = function() {
	jQuery('#' + this.dialogid).dialog("open");
};


