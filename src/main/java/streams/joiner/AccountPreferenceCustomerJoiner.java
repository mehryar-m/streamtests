//package streams.joiner;
//
//import com.mehryar.streamtests.accountpreference.AccountPreference;
//import com.mehryar.streamtests.customeraccount.CustomerAccount;
//import com.mehryar.streamtests.joinedaccountprefcustomeraccount.JoinedAccountPrefCustomerAccount;
//import org.apache.kafka.streams.kstream.ValueJoiner;
//
//public class AccountPreferenceCustomerJoiner
//        implements ValueJoiner<AccountPreference, CustomerAccount, JoinedAccountPrefCustomerAccount> {
//
//    @Override
//    public JoinedAccountPrefCustomerAccount apply(AccountPreference accountPreference, CustomerAccount customerAccount) {
//
//
//        JoinedAccountPrefCustomerAccount.Builder builder =
//                JoinedAccountPrefCustomerAccount.newBuilder();
//
//        builder.setCustomerId(customerAccount.getCustomerId());
//        builder.setAccountPreference(accountPreference.getAccountPreference());
//        builder.setAccountId(customerAccount.getAccount());
//
//        return builder.build();
//    }
//}package streams.joiner;
//
//import com.mehryar.streamtests.accountpreference.AccountPreference;
//import com.mehryar.streamtests.customeraccount.CustomerAccount;
//import com.mehryar.streamtests.joinedaccountprefcustomeraccount.JoinedAccountPrefCustomerAccount;
//import org.apache.kafka.streams.kstream.ValueJoiner;
//
//public class AccountPreferenceCustomerJoiner
//        implements ValueJoiner<AccountPreference, CustomerAccount, JoinedAccountPrefCustomerAccount> {
//
//    @Override
//    public JoinedAccountPrefCustomerAccount apply(AccountPreference accountPreference, CustomerAccount customerAccount) {
//
//
//        JoinedAccountPrefCustomerAccount.Builder builder =
//                JoinedAccountPrefCustomerAccount.newBuilder();
//
//        builder.setCustomerId(customerAccount.getCustomerId());
//        builder.setAccountPreference(accountPreference.getAccountPreference());
//        builder.setAccountId(customerAccount.getAccount());
//
//        return builder.build();
//    }
//}
