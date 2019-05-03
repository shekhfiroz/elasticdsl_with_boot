package sa.com.is.cyberwatch.web.repository;

import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import sa.com.is.cyberwatch.web.model.Customer;

@Service
public class ElasticSearchCustomerTemplate {

	@Autowired
	private ElasticsearchTemplate esTemplate;

	/*
	 * public User getUserById(String userId) { SearchQuery searchQuery = new
	 * NativeSearchQueryBuilder().withFilter(QueryBuilders.matchQuery("userid",
	 * userId)) .build(); List<User> users = esTemplate.queryForList(searchQuery,
	 * User.class); if (!users.isEmpty()) { return users.get(0); } return null; }
	 * 
	 * public User searchBy(String field, String value) { SearchQuery searchQuery =
	 * new NativeSearchQueryBuilder() .withQuery(QueryBuilders.matchQuery(field,
	 * value).fuzziness(Fuzziness.ONE).prefixLength(3)).build(); List<User> users =
	 * esTemplate.queryForList(searchQuery, User.class); if (!users.isEmpty()) {
	 * return users.get(0); } return null; }
	 */

	public List<Customer> getAllBy(String value) {
		QueryBuilder query = QueryBuilders.boolQuery()
				.should(QueryBuilders
						.queryStringQuery(value)
						.lenient(true)
						.field("id")
						.field("firstname")
						.field("lastname")
						.field("age"))
				.should(QueryBuilders
						.queryStringQuery("*" + value + "*")
						.lenient(true)
						.field("id")
						.field("firstname")
						.field("lastname")
						.field("age"));
		SearchQuery rootQuery = new NativeSearchQueryBuilder().withQuery(query).build();
		return esTemplate.queryForList(rootQuery, Customer.class);
	}

}
