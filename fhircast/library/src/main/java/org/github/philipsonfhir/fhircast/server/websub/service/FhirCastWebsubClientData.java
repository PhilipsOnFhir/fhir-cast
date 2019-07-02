package org.github.philipsonfhir.fhircast.server.websub.service;

import org.github.philipsonfhir.fhircast.support.websub.FhircastEventType;

import java.util.Set;
import java.util.TreeSet;

public class FhirCastWebsubClientData {
    String clientCallbackUrl;
    Set<FhircastEventType> subscriptions = new TreeSet<>();
    private String secret;
    private boolean verified;

    public FhirCastWebsubClientData(String clientCallbackUrl, String secret) {
        this.clientCallbackUrl = clientCallbackUrl;
        this.secret = secret;
    }

    public String getClientCallbackUrl() {
        return clientCallbackUrl;
    }

    public void subscribe(String subscribtions) {
        String[] subscriptions =  subscribtions.split( "," );
        for( String subscription: subscriptions){
            FhircastEventType fhircastEventType = FhircastEventType.get( subscription );
            this.subscriptions.add( fhircastEventType );
        }
    }

    public boolean hasSubscription(FhircastEventType eventType) {
        return subscriptions.contains( eventType );
    }

    public void unsubscribe(String subscribtions) {
        String[] subscriptions =  subscribtions.split( "," );
        for( String subscription: subscriptions){
            FhircastEventType fhircastEventType = FhircastEventType.get( subscription );
            this.subscriptions.remove( fhircastEventType );
        }
    }

    public String getHubSecret() {
        return this.secret;
    }

    public void setVerified(boolean success) {
        this.verified = success;
    }

    public boolean isVerified() {
        return this.verified;
    }
}
