const express =require('express');
const app = express();
const mysql =require('mysql');
/* 引入body-parser */
const bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

const connection = mysql.createConnection({
    host: '47.98.53.123',
    port:'3306',
    user: 'root',
    password: '',
    database: 'test'
});

connection.connect();

// 下面是解决跨域请求问题
app.all('*', function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "X-Requested-With");
    res.header("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
    res.header("X-Powered-By",' 3.2.1');
    res.header("Content-Type", "application/json;charset=utf-8");
    next();
 });

 app.get('/customer/selectAll',function(req,res){
     const sql ='select * from customer';
     connection.query(sql,function(err,result){
	 if(err){
	       console.log('select error -',err.message);
	       return;
         }
	 result = {
		isResult:true,
		result: result
	 };
         res.json(result);
     });
 })
 app.post('/customer/add',function(req,res){
     const sql = "insert into customer(phone,email,password) values(?,?,?)";
     var param = req.body;
	 console.log(param);
     connection.query(sql,[param.phone,param.email,param.password],function(err,result){
	     console.log(result);
	    if(err){
		    result = {
			isResult:false,
			result:null
		    };
		    console('insert err-',err.message);
		    return;
	    }
	     result = {
		isResult:true,
		result:result
	     };
	     console.log(result);
	    res.json(result);
    });
 })
 app.post('/customer/count',function(req,res){
	const sql = "select count(*) as count from customer where phone=? and password=?";
	var body = req.body;
	connection.query(sql,[body.phone,body.password],function(err,result){
	console.log(result);
	if(err){
		result = {
			isResult:false,
			result:null
		};
		console.log('count err',err.message);
		return;
	}
	result = {
		isResult:true,
		result:result
	};
	res.json(result);
	});
 })
 
 app.get('/store/selectAll',function(req,res){
	const sql = 'select * from store_profile';
	connection.query(sql,function(err,result){
		if(err){
			    console.log('select error -',err.message);
			    return;
		 }
		result = {
		          isResult:true,
		          result: result
		         };
		res.json(result);
	});
 })
 app.get('/commodity/selectById',function(req,res){
	         const sql = 'select * from commodity_profile where store_id = ?';
	          connection.query(sql,[req.query.store_id],function(err,result){
	                  if(err){
	                         console.log('select error -',err.message);
	                          return;
		                  }
	                   result = {
	                           isResult : true,
	                            result:result
		                    };
	                   res.json(result);
	           });
	  })
 app.post('/shoppingcar/insert',function(req,res){
	const sql = 'insert into shopping_car (number,commodity_id,store_id,customer_id) values(1,?,?,?)';
	var body = req.body;
	connection.query(sql,[body.commodity_id,body.store_id,body.customer_id],function(err,result){
		if(err){
			console.log('insert error',err.msessage);
			return;
		}
		result = {
			isResult:true,
			result:result
		};
		res.json(result);
	});
 })
 app.post('/shoppingcar/add',function(req,res){
	const sql = 'update shopping_car set number = number+1 where commodity_id =? and customer_id = ?';
	var body = req.body;
	connection.query(sql,[body.commodity_id,body.customer_id],function(err,result){
		if(err){
			console.log('update shoppingcar err',err.message);
			return;
		}
		result = {
			isResult:true,
			result:result
		};
		res.json(result);
	});
 })
 app.post('/shoppingcar/subtract',function(req,res){
         const sql = 'update shopping_car set number = number-1 where commodity_id =? and customer_id = ?';
         var body = req.body;
         connection.query(sql,[body.commodity_id,body.customer_id],function(err,result){
                 if(err){
                         console.log('update shoppingcar err',err.message);
                         return;
                 }
                 result = {
                         isResult:true,
                         result:result
	                 };
                 res.json(result);
         });
 })
 app.post('/shoppingcar/delete',function(req,res){
         const sql = 'delete from shopping_car where commodity_id =? and customer_id = ?';
         var body = req.body;
         connection.query(sql,[body.commodity_id,body.customer_id],function(err,result){
                 if(err){
                         console.log('delete shoppingcar err',err.message);
                         return;
	                 }
                 result = {
                        isResult:true,
                        result:result
                 };
                 res.json(result);
         });
 })
 app.post('/shoppingcar/deleteAll',function(req,res){
          const sql = 'delete from shopping_car where store_id =? and customer_id = ?';
          var body = req.body;
          connection.query(sql,[body.store_id,body.customer_id],function(err,result){
                   if(err){
                          console.log('delete shoppingcar err',err.message);
                          return;
                    }
                   result = {
	                   isResult:true,
                           result:result
                   };
                   res.json(result);
          });
})
 app.post('/shoppingcar/selectAll',function(req,res){
           const sql = 'select commodity_id, commodity_profile.name,commodity_profile.cover,commodity_profile.price,number from shopping_car ,commodity_profile where shopping_car.commodity_id = commodity_profile.id  and shopping_car.store_id =? and customer_id = ?';
           var body = req.body;
           connection.query(sql,[body.store_id,body.customer_id],function(err,result){
                     if(err){
                            console.log('select shoppingcar err',err.message);
                            return;
                      }
                      result = {
                               isResult:true,
                               result:result
                       };
	               res.json(result);
	   });
 })
 var server = app.listen(3000,'0.0.0.0',function(){
     var host = server.address().address;
     var port =server.address().port;
     console.log("地址为http://%s:%s",host,port);
 })
