<!DOCTYPE html>
<html lang="zh-cn">
<head>
<#include "/base/head_meta.ftl"/>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>编辑</strong></div>
  <div class="body-content">
    <form id="form" method="post" class="form-x" action="update">
        <input type="hidden" id="id" name="id" value="${vo.id}" />
      <div class="form-group">
        <div class="label">
          <label>路径：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${vo.url!''}" name="url" <#--data-validate="required:请输入路径"--> />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>版本号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${vo.version!''}" name="version" data-validate="required:请输入版本号" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>设备系统：</label>
        </div>
        <div class="field">
          <#--<input type="text" class="input w50" value="${vo.deviceType}" name="deviceType" data-validate="required:请输入设备系统" />-->
            <select name="deviceType" class="input w50">
                <#--<option value="">请选择设备系统</option>-->
                <option value="ANDROID" <#if vo.deviceType=='ANDROID'>selected</#if> >ANDROID</option>
                <option value="APAD" <#if vo.deviceType=='APAD'>selected</#if>>APAD</option>
                <option value="IOS" <#if vo.deviceType=='IOS'>selected</#if>>IOS</option>
                <option value="IPAD" <#if vo.deviceType=='IPAD'>selected</#if>>IPAD</option>
            </select>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit">更新</button>
          <button class="button bg-main icon-times-circle" type="button" onclick="history.back(-1)" width="105px;"> 取消</button>
        </div>
      </div>
    </form>
  </div>
</div>

</body></html>