/* Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wslite.http

import wslite.util.ObjectHelper

class HTTPRequest {

    URL url
    HTTPMethod method

    int connectTimeout = 0
    int readTimeout = 0
    boolean followRedirects = true
    boolean useCaches
    boolean sslTrustAllCerts
    String sslTrustStoreFile
    String sslTrustStorePassword

    Proxy proxy

    Map<String, String> headers = new HTTPHeaderMap<String, String>()

    byte[] data = null

    boolean isConnectTimeoutSet
    boolean isReadTimeoutSet
    boolean isUseCachesSet
    boolean isFollowRedirectsSet
    boolean isSSLTrustAllCertsSet

    void setHeaders(Map<String, String> map) {
        headers = new HTTPHeaderMap(map)
    }

    void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout
        isConnectTimeoutSet = true
    }

    void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout
        isReadTimeoutSet = true
    }

    void setUseCaches(boolean useCaches) {
        this.useCaches = useCaches
        isUseCachesSet = true
    }

    void setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects
        isFollowRedirectsSet = true
    }

    void setSslTrustAllCerts(boolean sslTrustAllCerts) {
        this.sslTrustAllCerts = sslTrustAllCerts
        isSSLTrustAllCertsSet = true
    }

    String getContentAsString() {
        if (!data) {
            return ''
        }
        return new String(data, charset)
    }

    private String getCharset() {
        return contentTypeHeader.charset ?: HTTP.DEFAULT_CHARSET
    }

    private ContentTypeHeader getContentTypeHeader() {
        return new ContentTypeHeader(headers[HTTP.CONTENT_TYPE_HEADER])
    }

    @Override
    String toString() {
        def excludes = ['isConnectTimeoutSet', 'isReadTimeoutSet',
                        'isUseCachesSet', 'isFollowRedirectsSet',
                        'isSSLTrustAllCertsSet', 'sslTrustStorePassword',
                        'data', 'contentAsString', 'charset', 'contentTypeHeader']
        ObjectHelper.dump(this, exclude:excludes)
    }

}
