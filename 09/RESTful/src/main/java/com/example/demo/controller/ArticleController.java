package com.example.demo.controller;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.result.ExceptionMsg;
import com.example.demo.result.Response;
import com.example.demo.result.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Author:   longzhonghua
 * Date:     3/24/2019 9:22 AM
 * Description: ${DESCRIPTION}
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    protected Response result(ExceptionMsg msg){
        return new Response(msg);
    }
    protected Response result(){
        return new Response();
    }

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseData  getArticleList() {
        List<Article> list = new ArrayList<Article>(articleRepository.findAll());
        return new ResponseData(ExceptionMsg.SUCCESS,list);

    }

    //增
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseData add(Article article) {
        articleRepository.save(article);
       // return "{success:true,message: \"添加成功\" }";
        return new ResponseData(ExceptionMsg.SUCCESS,article);
    }


    //删
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable("id") long id) {

     /*   RestTemplate client= restTemplateBuilder.build();
        String uri = "http://localhost:8080" + "/{id}";
        Map map= new HashMap();
        map. put ("orderid",id);
        Void article = client.delete(uri,map,id);*/
        articleRepository.deleteById(id);

        return result(ExceptionMsg.SUCCESS);
        //return new ResponseData(ExceptionMsg.SUCCESS,"");
      }


    //改
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseData update(Article model) {
        articleRepository.save(model);
        return new ResponseData(ExceptionMsg.SUCCESS,model);
    }

    //查
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseData findArticle(@PathVariable("id") Integer id) throws IOException {
              Article article = articleRepository.findById(id);
        if (article != null) {
            return new ResponseData(ExceptionMsg.SUCCESS,article);
        }
        return new ResponseData(ExceptionMsg.FAILED,article);
    }
    //查
    @RequestMapping(value = "/re/{id}", method = RequestMethod.GET)
    public Article findArticled(@PathVariable("id") Integer id) throws IOException {
        RestTemplate client= restTemplateBuilder.build();
        String uri = "http://localhost:8080/article/"+id;
        System.out.println(uri);
        Article article = client.getForObject (uri,Article.class,id) ;

        return article;
    }
}


