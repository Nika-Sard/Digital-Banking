import com.example.demo.dao.Dao;
import com.example.demo.model.Account;
import com.example.demo.model.Obshiaki;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
public class daoTests {

    private void addUsers(int num, Dao dao) {
        for(int i = 0; i < num; i++) {
            String userName = "user" + Integer.toString(i);
            dao.addUser(userName);
        }
    }
    @Test
    public void testUser() {
        Dao dao = new Dao();
        addUsers(10, dao);

        User user0 = dao.getUser("0");
        User user9 = dao.getUser("9");

        assertEquals(user0.getUserId(), "0");
        assertEquals(user9.getUserId(), "9");
        assertEquals(user0.getName(), "user0");
        assertEquals(user9.getName(), "user9");
    }

    @Test
    public void testAccount() {
        Dao dao = new Dao();
        addUsers(10, dao);

        String accountId1 = dao.addAccount(true);
        String accountId2 = dao.addAccount(true);

        dao.addUserAccount("1", accountId1);
        dao.addUserAccount("1", accountId2);

        assertEquals(dao.getAccount(accountId1).getAccountId() , accountId1);
        assertEquals(dao.getAccount(accountId1).getBalance() , 0);

        Account account = dao.getAccount(accountId1);
        account.deposit(100);
        account.withdraw(25);
        assertEquals(account.getBalance(), 75);

        dao.deposit(accountId1, 100);
        dao.withdraw(accountId1, 25);
        assertEquals(dao.getAccount(accountId1).getBalance(), 75);

        dao.addUserAccount("0", accountId1);
        assertEquals(dao.getUser("0").getAccounts().getFirst(), accountId1);

        dao.addUserAccount("0", accountId2);
        assertEquals(dao.getUser("0").getAccounts().size(), 2);
    }

    @Test
    public void testObshiak() {
        Dao dao = new Dao();
        addUsers(10, dao);

        String accountId1 = dao.addAccount(false);

        dao.addUserAccount("1", accountId1);
        dao.addUserAccount("2", accountId1);

        assertEquals(dao.getAccount(accountId1).getAccountId() , accountId1);
        assertEquals(dao.getAccount(accountId1).getBalance() , 0);

        dao.addAccountUser("1", accountId1);
        dao.addAccountUser("2", accountId1);

        Obshiaki obsh = (Obshiaki) dao.getAccount(accountId1);
        assertEquals(obsh.getOwnerIds().getFirst(), "1");
    }

    @Test
    public void testTransaction() {
        Dao dao = new Dao();
        addUsers(10, dao);

        String accountId1 = dao.addAccount(true);
        String accountId2 = dao.addAccount(true);

        dao.addUserAccount("1", accountId1);
        dao.addUserAccount("2", accountId2);

        dao.deposit(accountId1, 100);
        dao.deposit(accountId2, 100);

        String transactionId = dao.addTransaction(accountId1, accountId2, null, 20);
        Transaction transaction = dao.getTransaction(transactionId);
        assertEquals(transaction.getTransactionId(), transactionId);
        assertEquals(transaction.getAmount(), 20);
        assertNull(transaction.getMessage());
    }

    @Test
    public void testRequestManager() {
        Dao dao = new Dao();
        addUsers(10, dao);

        ArrayList<String> owners = new ArrayList<>();
        owners.add("1");
        owners.add("2");

        String accountId1 = dao.addAccount(false);
        String accountId2 = dao.addAccount(true);

        dao.addUserAccount("1", accountId1);
        dao.addUserAccount("2", accountId1);
        dao.addUserAccount("0", accountId2);

        dao.deposit(accountId1, 100);

        String transactionId = dao.addTransaction(accountId1, accountId2, null, 20);

        String requestManagerId = dao.addRequestManager(owners, transactionId, "m");
        assertEquals(dao.getRequestManager(requestManagerId).getMessage(), "m");
        assertEquals(dao.getRequestManager(requestManagerId).getRequestManagerId(), requestManagerId);
        assertEquals(dao.getRequestManager(requestManagerId).getTransaction().getTransactionId(), transactionId);

        dao.deleteRequestManager(requestManagerId);
        try{
            dao.getRequestManager(requestManagerId);
            fail("requestManager Id still exists");
        } catch(RuntimeException ignored) {
            System.out.println("Good");
        }
    }

    @Test
    public void testRequest() {
        Dao dao = new Dao();
        addUsers(10, dao);

        ArrayList<String> owners = new ArrayList<>();
        owners.add("1");
        owners.add("2");

        String accountId1 = dao.addAccount(false);
        String accountId2 = dao.addAccount(true);

        dao.addUserAccount("1", accountId1);
        dao.addUserAccount("2", accountId1);
        dao.addUserAccount("0", accountId2);

        dao.deposit(accountId1, 100);

        String transactionId = dao.addTransaction(accountId1, accountId2, null, 20);
        String requestManagerId = dao.addRequestManager(owners, transactionId, "m");

        String requestId = dao.addRequest("2", requestManagerId, transactionId, "m");

        assertEquals(dao.getRequest(requestId).getRequestReceiverId(), "2");
        assertEquals(dao.getRequest(requestId).getMessage(), "m");
        assertEquals(dao.getRequest(requestId).getManager().getRequestManagerId(), requestManagerId);
        assertEquals(dao.getRequest(requestId).getTransaction().getTransactionId(), transactionId);

        assertEquals(dao.getRequestsByUser("2").getFirst().getRequestId(), requestId);

        dao.deleteRequest(requestId);
        try{
            dao.getRequest(requestId);
            fail("request Id still exists");
        } catch(RuntimeException ignored) {
        }
    }

    @Test
    public void testFinal() {
        Dao dao = new Dao();
        addUsers(10, dao);
        ArrayList<String> owners = new ArrayList<>();
        owners.add("1");
        owners.add("2");
        String accountId1 = dao.addAccount(false);
        String accountId2 = dao.addAccount(true);
        String transactionId = dao.addTransaction(accountId1, accountId2, null, 20);
        String requestManagerId = dao.addRequestManager(owners, transactionId, "m");

        dao.addApprovedRequestReceiver(requestManagerId, "1");
        assertFalse(dao.getRequestManager(requestManagerId).hasEveryoneApproved());

        dao.addApprovedRequestReceiver(requestManagerId, "2");
        assertTrue(dao.getRequestManager(requestManagerId).hasEveryoneApproved());
    }
    @Test
    public void testRemove() {
        Dao dao = new Dao();
        addUsers(10, dao);

        ArrayList<String> owners = new ArrayList<>();
        owners.add("1");
        owners.add("2");

        String accountId1 = dao.addAccount(false);
        String accountId2 = dao.addAccount(true);

        dao.addUserAccount("1", accountId1);
        dao.addUserAccount("2", accountId1);

        Obshiaki obs = (Obshiaki) dao.getAccount(accountId1);

        String transactionId = dao.addTransaction(accountId1, accountId2, "", 20);
        String requestManagerId = dao.addRequestManager(owners, transactionId, "m");
        String requestId = dao.addRequest("2", requestManagerId, transactionId, "m");
    }
}
