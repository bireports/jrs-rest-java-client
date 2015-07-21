package jaspersoft.jasperserver.jaxrs.client.apiadapters.authority.users;

import com.jaspersoft.jasperserver.dto.authority.ClientUser;

import com.jaspersoft.jasperserver.dto.authority.UsersListWrapper;
import com.jaspersoft.jasperserver.jaxrs.client.core.*;
import junit.framework.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Alexander Krasnyanskiy
 */
public class UsersServiceIT {
    private RestClientConfiguration config;
    private JasperserverRestClient client;
    private Session session;

    @BeforeMethod
    public void before() {
        config = new RestClientConfiguration("http://localhost:4444/jasperserver-pro");
        config.setAcceptMimeType(MimeType.JSON);
        config.setContentMimeType(MimeType.JSON);
        config.setJrsVersion(JRSVersion.v6_0_0);
        client = new JasperserverRestClient(config);
        session = client.authenticate("superuser", "superuser");
    }



    @Test
    public void shouldReturnAllUsers() {

        // When
        List<ClientUser> users = session
                .usersService()
                .allUsers()
                .get()
                .getEntity()
                .getUserList();

        // Then
        Assert.assertTrue(users.size() > 3);
    }

    @AfterMethod
    public void after() {
        session.logout();
        session = null;
    }
}