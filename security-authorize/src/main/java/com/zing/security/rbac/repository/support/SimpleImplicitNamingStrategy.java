package com.zing.security.rbac.repository.support;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;

public class SimpleImplicitNamingStrategy extends ImplicitNamingStrategyComponentPathImpl {

    private static final long serialVersionUID = -5150716965738843078L;

    @Override
    protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
        return super.toIdentifier("zing_" + stringForm, buildingContext);
    }
}
