package org.wy.helloworld.es;

import java.util.List;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogConntroller {

	@Autowired
	EsBlogRepo esBlogRepo;
	@RequestMapping("/blogs")
	public List<EsBlog> list(
			@RequestParam(value = "title", required = false,defaultValue="") String title,
			@RequestParam(value = "summary", required = false,defaultValue="") String summary,
			@RequestParam(value = "content", required = false,defaultValue="") String content,
			@RequestParam(value = "pageIndex", required = false,defaultValue="0") int pageIndex,
			@RequestParam(value = "pageSize", required = false,defaultValue="10") int pageSize) {
		//delete
        System.out.println("starting to request server ....");
        System.out.println(System.currentTimeMillis());
        esBlogRepo.deleteAll();
		//init data
		esBlogRepo.save(new EsBlog("Had I not seen the Sun","I could have borne the shade",
				"But Light a newer Wilderness.My Wilderness has made"));
		esBlogRepo.save(new EsBlog("There is room in the halls of pleasure", 
				"For a long time and Lordly train", "But one by we must all the file on,Through the narrow aisles of pain."));
		esBlogRepo.save(new EsBlog("When you are old","When you are old and grey and full of sleep",
				"and nodding by fire,take down this book"));  
		Pageable pageable=new PageRequest(pageIndex,pageSize);
		Page<EsBlog> page=esBlogRepo.findByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content, pageable);
        System.out.println("end to request server !!!");
        System.out.println(System.currentTimeMillis());
		return page.getContent();
	}
}
