package com.sysuser.oauth2.filter;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.sysuser.model.oauth2.Client;
import com.sysuser.utils.CommonUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author yuit
 * @date 2018/10/16  15:36
 *
 **/
@Data
@SuppressWarnings("unchecked")
public final class MyClientDetails implements ClientDetails {

    private Client client;
    private Set<String> scope;

    public MyClientDetails(Client client) {
        this.client = client;
    }

    public MyClientDetails() {
    }

    @Override
    public String getClientId() {
        return client.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return client.getResourceIds()!=null?
                CommonUtils.transformStringToSet(client.getResourceIds(),String.class):null;
    }


    @Override
    public String getClientSecret() {
        return client.getClientSecret();
    }


    @Override
    public Set<String> getScope() {

        this.scope = client.getScope()!=null?
                CommonUtils.transformStringToSet(client.getScope(),String.class):null;

        return client.getScope()!=null?
                CommonUtils.transformStringToSet(client.getScope(),String.class):null;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return client.getAuthorizedGrantTypes()!=null?
                CommonUtils.transformStringToSet(client.getAuthorizedGrantTypes(),String.class):null;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return client.getRegisteredRedirectUri()!=null?
                CommonUtils.transformStringToSet(client.getRegisteredRedirectUri(),String.class):null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return (client.getAuthorities()!=null&&client.getAuthorities().trim().length()>0)?
                AuthorityUtils.commaSeparatedStringToAuthorityList(client.getAuthorities()):null;
    }


    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

	@Override
	public boolean isSecretRequired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isScoped() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		// TODO Auto-generated method stub
		return false;
	}
}
