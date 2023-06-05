// Copyright 2021-present StarRocks, Inc. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


package com.starrocks.sql.ast;

import com.starrocks.alter.AlterOpType;
import com.starrocks.common.Pair;
import com.starrocks.sql.parser.NodePosition;
import org.apache.commons.lang.NotImplementedException;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BackendClause extends AlterClause {
    protected List<String> hostPorts;

    protected List<Pair<String, Integer>> hostPortPairs;

    // TODO: need to remove this change after be split into cn + dn
    protected String warehouseName;

    protected BackendClause(List<String> hostPorts, NodePosition pos) {
        super(AlterOpType.ALTER_OTHER, pos);
        this.hostPorts = hostPorts;
        this.hostPortPairs = new LinkedList<>();
    }

    // TODO: need to remove this change after be split into cn + dn
    protected BackendClause(List<String> hostPorts, NodePosition pos, String warehouseName) {
        this(hostPorts, pos);
        this.warehouseName = warehouseName;
    }

    public List<Pair<String, Integer>> getHostPortPairs() {
        return hostPortPairs;
    }

    public List<String> getHostPorts() {
        return hostPorts;
    }

    // TODO: need to remove this change after be split into cn + dn
    public String getWarehouseName() {
        return warehouseName;
    }

    @Override
    public Map<String, String> getProperties() {
        throw new NotImplementedException();
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitBackendClause(this, context);
    }
}
