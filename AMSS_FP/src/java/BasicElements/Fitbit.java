/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicElements;

/**
 *
 * @author Luna
 */
public class Fitbit {
    private int idFitbitUserData;
    private String FITBIT_URL,FITBIT_API_URL,OAUTH_CLIENTID,CLIENT_SECRET,REDIRECT_URI,EXPIRATION_TIME;

    public Fitbit(int idFitbitUserData, String FITBIT_URL, String FITBIT_API_URL, String OAUTH_CLIENTID, String CLIENT_SECRET, String REDIRECT_URI, String EXPIRATION_TIME) {
        this.idFitbitUserData = idFitbitUserData;
        this.FITBIT_URL = FITBIT_URL;
        this.FITBIT_API_URL = FITBIT_API_URL;
        this.OAUTH_CLIENTID = OAUTH_CLIENTID;
        this.CLIENT_SECRET = CLIENT_SECRET;
        this.REDIRECT_URI = REDIRECT_URI;
        this.EXPIRATION_TIME = EXPIRATION_TIME;
    }

    public String getOAUTH_CLIENTID() {
        return OAUTH_CLIENTID;
    }

    public void setOAUTH_CLIENTID(String OAUTH_CLIENTID) {
        this.OAUTH_CLIENTID = OAUTH_CLIENTID;
    }

    public String getCLIENT_SECRET() {
        return CLIENT_SECRET;
    }

    public void setCLIENT_SECRET(String CLIENT_SECRET) {
        this.CLIENT_SECRET = CLIENT_SECRET;
    }

    public String getREDIRECT_URI() {
        return REDIRECT_URI;
    }

    public void setREDIRECT_URI(String REDIRECT_URI) {
        this.REDIRECT_URI = REDIRECT_URI;
    }


    public int getIdFitbitUserData() {
        return idFitbitUserData;
    }

    public void setIdFitbitUserData(int idFitbitUserData) {
        this.idFitbitUserData = idFitbitUserData;
    }

    public String getFITBIT_URL() {
        return FITBIT_URL;
    }

    public void setFITBIT_URL(String FITBIT_URL) {
        this.FITBIT_URL = FITBIT_URL;
    }

    public String getFITBIT_API_URL() {
        return FITBIT_API_URL;
    }

    public void setFITBIT_API_URL(String FITBIT_API_URL) {
        this.FITBIT_API_URL = FITBIT_API_URL;
    }

    public String getEXPIRATION_TIME() {
        return EXPIRATION_TIME;
    }

    public void setEXPIRATION_TIME(String EXPIRATION_TIME) {
        this.EXPIRATION_TIME = EXPIRATION_TIME;
    }
    
}
