package com.smockin.admin.service;

import com.smockin.admin.dto.LiveLoggingBlockingEndpointDTO;
import com.smockin.admin.exception.AuthException;
import com.smockin.admin.exception.MockImportException;
import com.smockin.admin.exception.RecordNotFoundException;
import com.smockin.admin.exception.ValidationException;
import com.smockin.admin.persistence.enums.ServerTypeEnum;
import com.smockin.mockserver.dto.MockServerState;
import com.smockin.mockserver.dto.MockedServerConfigDTO;
import com.smockin.mockserver.dto.ProxyForwardConfigDTO;
import com.smockin.mockserver.exception.MockServerException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * Created by mgallina.
 */
public interface MockedServerEngineService {

    MockedServerConfigDTO startRest(final String token) throws MockServerException, RecordNotFoundException, AuthException;
    MockServerState getRestServerState() throws MockServerException;
    void shutdownRest(final String token) throws MockServerException, RecordNotFoundException, AuthException;
    MockedServerConfigDTO restartRest(final String token) throws MockServerException, RecordNotFoundException, AuthException;

    MockedServerConfigDTO loadServerConfig(final ServerTypeEnum serverType) throws RecordNotFoundException;
    void saveServerConfig(final ServerTypeEnum serverType, final MockedServerConfigDTO config, final String token) throws RecordNotFoundException, AuthException, ValidationException;
    void handleServerAutoStart();

    ProxyForwardConfigDTO loadProxyForwardConfig(final ServerTypeEnum type);
    void saveProxyForwardMappings(final ServerTypeEnum serverType,
                                  final ProxyForwardConfigDTO proxyForwardConfigDTO,
                                  final String token) throws AuthException, ValidationException, RecordNotFoundException;

    void addLiveLoggingPathToBlock(final LiveLoggingBlockingEndpointDTO liveLoggingBlockingEndpoint,
                                   final String token) throws AuthException;

    void removeLiveLoggingPathToBlock(final String method,
                                      final String path,
                                      final String token) throws AuthException;

    Optional<String> exportProxyMappings(final String token)
            throws AuthException;

    String importProxyMappingsFile(final MultipartFile file, final boolean keepExisting, final String token)
            throws MockImportException, AuthException, ValidationException;

}
