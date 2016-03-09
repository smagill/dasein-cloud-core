/**
 * Copyright (C) 2009-2015 Dell, Inc.
 * See annotations for authorship information
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.dasein.cloud.util.products.model;

import java.util.Map;

public class VmProduct {
    private String                id;
    private String                name;
    private String                description;
    private boolean               current;
    private int                   rootVolumeSizeInGb;
    private int                   ramSizeInMb;
    private int                   cpuCount;
    private String[]              architectures;
    private String[]              excludesRegions;
    private Map<String, Object>   customProperties;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent( boolean current ) {
        this.current = current;
    }

    public int getRootVolumeSizeInGb() {
        return rootVolumeSizeInGb;
    }

    public void setRootVolumeSizeInGb( int rootVolumeSizeInGb ) {
        this.rootVolumeSizeInGb = rootVolumeSizeInGb;
    }

    public int getRamSizeInMb() {
        return ramSizeInMb;
    }

    public void setRamSizeInMb( int ramSizeInMb ) {
        this.ramSizeInMb = ramSizeInMb;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount( int cpuCount ) {
        this.cpuCount = cpuCount;
    }

    public String[] getArchitectures() {
        return architectures;
    }

    public void setArchitectures( String[] architectures ) {
        this.architectures = architectures;
    }

    public Map<String, Object> getCustomProperties() {
        return customProperties;
    }

    public void setCustomProperties( Map<String, Object> customProperties ) {
        this.customProperties = customProperties;
    }

    public String[] getExcludesRegions() {
        return excludesRegions;
    }

    public void setExcludesRegions( String[] excludesRegions ) {
        this.excludesRegions = excludesRegions;
    }

}
