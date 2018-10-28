package org.wy.helloworld.es;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsBlogRepo extends ElasticsearchRepository<EsBlog, String> {
	Page<EsBlog> findByTitleContainingOrSummaryContainingOrContentContaining(
			String title, String summary, String content, Pageable pageable);
}
