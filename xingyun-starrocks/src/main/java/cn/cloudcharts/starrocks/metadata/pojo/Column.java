/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package cn.cloudcharts.starrocks.metadata.pojo;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
public class Column implements Serializable {

    private static final long serialVersionUID = 6438514547501611599L;

    private String name;
    private String type;
    private String comment;
    private boolean keyFlag;
    private boolean autoIncrement;
    private String defaultValue;
    private boolean isNullable;
    private String columnFamily;
    private Integer position;
    private Integer length;
    private Integer precision;
    private Integer scale;
    private String characterSet;
    private String collation;

}
