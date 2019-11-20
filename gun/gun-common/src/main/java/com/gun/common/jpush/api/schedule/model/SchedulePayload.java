package com.gun.common.jpush.api.schedule.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cn.jiguang.common.utils.StringUtils;

public class SchedulePayload implements IModel {

    private static Gson gson = new Gson();

    private String name;
    private Boolean enabled;
    private TriggerPayload trigger;

    private SchedulePayload(String name, Boolean enabled, TriggerPayload trigger) {
        this.name = name;
        this.enabled = enabled;
        this.trigger = trigger;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public JsonElement toJSON() {
        JsonObject json = new JsonObject();
        if ( StringUtils.isNotEmpty(name) ) {
            json.addProperty("name", name);
        }
        if ( null != enabled ) {
            json.addProperty("enabled", enabled);
        }
        if ( null != trigger ) {
            json.add("trigger", trigger.toJSON());
        }
        return json;
    }

    @Override
    public String toString() {
        return gson.toJson(toJSON());
    }

    public static class Builder{
        private String name;
        private Boolean enabled;
        private TriggerPayload trigger;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEnabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder setTrigger(TriggerPayload trigger) {
            this.trigger = trigger;
            return this;
        }

        public SchedulePayload build() {

            return new SchedulePayload(name, enabled, trigger);
        }
    }
}
