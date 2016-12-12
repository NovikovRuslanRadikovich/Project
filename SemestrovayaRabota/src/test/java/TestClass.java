import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.itis.kpfu.Novikov_Ruslan.DAO.UserDao;
import ru.itis.kpfu.Novikov_Ruslan.DAO.UserServiceImpl;
import ru.itis.kpfu.Novikov_Ruslan.models.User;
import ru.itis.kpfu.Novikov_Ruslan.servlets.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;


public class TestClass {
    static User[] users;
   
    @BeforeClass
    public static void initialization(){
        users = new User[3];
        users[0]  = new User("Sidney","Crosby",true,"87878787","08.08.1987","Pittsburgh");
        users[1] = new User("Jonathan","Toews",true,"19191919","14.04.1988","Chicago");
        users[2] = new User("Connor","McDavid",true,"97979797","12.02.1997","Edmonton");
    }

    @Test
    public void ServletTest() throws Exception{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response =  mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("admin")).thenReturn("admin");
        when(request.getSession()).thenReturn(session);

        when(request.getParameter("action")).thenReturn("delete");
        when(request.getParameter("id")).thenReturn("0");
        PrintWriter writer = new PrintWriter("myfile.txt");
        when(response.getWriter()).thenReturn(writer);
        Admin ad = mock(Admin.class);
        ad.doGet(request,response);
        writer.print(request.getParameter("action"));
        verify(request,atMost(1)).getParameter("action");
        verify(request,atMost(1)).getParameter("id");
        writer.flush();
        Assert.assertEquals(true,FileUtils.readFileToString(new File("myfile.txt"), "UTF-8").contains("delete"));
    }

    @Test
    public void UserDaoTest() {
        UserDao MyUserDaoTest = mock(UserDao.class);
        when(MyUserDaoTest.getAll()).thenReturn(users);
        UserServiceImpl UserService = new UserServiceImpl(MyUserDaoTest);
        Assert.assertEquals(UserService.isRegistered("Connor"),true);
        Assert.assertFalse(UserService.isRegistered("Kane"));
        Assert.assertTrue(UserService.isRegistered("Jonathan"));
    }

}