package cn.bblink.monitor.util;

import cn.bblink.common.vo.Page;
import org.apache.commons.collections.MapUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class DaoTemplate {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private SqlSessionTemplate sqlSession;

	/**
	 * 分页
	 */
	public <E> Page<E> paging(String statement, Map<String, Object> parameter) {
		Long totalCount = (Long)this.selectOne(statement + "Count", parameter);
		if (totalCount == 0) {
			return new Page();
		}
		int page = MapUtils.getIntValue(parameter, "page");
		Integer pageSize = MapUtils.getInteger(parameter, "pageSize", Page.DEFAULT_PAGE_SIZE);
		Page pageVo = new Page(totalCount, pageSize, page);
		parameter.put("page", page);//第几页
		if (!parameter.containsKey("pageSize")) {
			parameter.put("pageSize", 20);
		}
		pageVo.setItems(this.selectList(statement, parameter));
		return pageVo;
	}
	
	public <E> List<E> selectList(String statement) {
		return sqlSession.selectList(statement);
	}
	
	public <E> List<E> selectList(String statement, Object parameter) {
		return sqlSession.selectList(statement, parameter);
	}

	public <T> T selectOne(String statement) {
		return sqlSession.selectOne(statement);
	}

	public <T> T selectOne(String statement, Object parameter) {
		return sqlSession.selectOne(statement, parameter);
	}
	
	public int insert(String statement) {
		return sqlSession.insert(statement);
	}
	
	public int insert(String statement, Object parameter) {
		return sqlSession.insert(statement, parameter);
	}

	public int update(String statement) {
		return sqlSession.update(statement);
	}
	
	public int update(String statement, Object parameter) {
		return sqlSession.update(statement, parameter);
	}

	public int delete(String statement) {
		return sqlSession.delete(statement);
	}
	
	public int delete(String statement, Object parameter) {
		return sqlSession.delete(statement, parameter);
	}

	/**
	 * 获取所有数据
	 */
	public <E> Page<E> allData(String statement, Map<String, Object> parameter) {
		Long totalCount = (Long)this.selectOne(statement + "Count", parameter);
		if (totalCount == 0) {
			return new Page();
		}
		int page = 0;
		Integer pageSize = 100000;
		Page pageVo = new Page(totalCount, pageSize, page);
		parameter.put("page", 0);//第几页
		if (!parameter.containsKey("pageSize")) {
			parameter.put("pageSize", 100000);
		}
		pageVo.setItems(this.selectList(statement, parameter));
		return pageVo;
	}
}	