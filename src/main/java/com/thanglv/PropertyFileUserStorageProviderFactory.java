/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
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
 */

package com.thanglv;

import org.jboss.logging.Logger;
import org.keycloak.common.util.EnvUtil;
import org.keycloak.component.ComponentModel;
import org.keycloak.component.ComponentValidationException;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.keycloak.storage.UserStorageProviderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class PropertyFileUserStorageProviderFactory implements UserStorageProviderFactory<PropertyFileUserStorageProvider> {
    public static final String PROVIDER_NAME = "Recruitery DB";

    protected static final List<ProviderConfigProperty> configMetadata;

    static {
        configMetadata = ProviderConfigurationBuilder.create()
                .property().name("jdbcUrl").type(ProviderConfigProperty.STRING_TYPE).label("JdbcUrl").defaultValue("").helpText("Đường dẫn kết nối tới database (Postgresql)")
                .add()
                .property().name("dbUsername").type(ProviderConfigProperty.STRING_TYPE).label("DB user").defaultValue("").helpText("Tên đăng nhập database")
                .add()
                .property().name("dbPassword").type(ProviderConfigProperty.STRING_TYPE).label("DB password").defaultValue("").helpText("Mật khẩu database").secret(true)
                .add()
                .property().name("dbName").type(ProviderConfigProperty.STRING_TYPE).label("DB name").defaultValue("").helpText("Tên database")
                .add()
                .property().name("tableName").type(ProviderConfigProperty.STRING_TYPE).label("Table name").defaultValue("").helpText("Tên table user")
                .add()
                .build();
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configMetadata;
    }

    @Override
    public void validateConfiguration(KeycloakSession session, RealmModel realm, ComponentModel config) throws ComponentValidationException {

    }

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }

    @Override
    public PropertyFileUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        Properties props = new Properties();
        return new PropertyFileUserStorageProvider(session, model, props);
    }
}
