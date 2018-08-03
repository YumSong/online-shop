#项目模块

>online-shop

>>core

>>>admin

>>>client

>>>merchant

**WebService interfaces**

    let merchantServer = "http://localhost:3000";
    
    let clientServer = "http://localhost:3000";
    
    let imgServer = "http://localhost:3000";
    
    
    Merchant {
      // update shop
      updateShop: {
        url: "/shop/",
        method: 'PUT',
        requestData: {
            Shop-jsonString(contain merchantDetailId)
        },
        responseData: {
            ResultBody-jsonString
        }
      }
      
      // get shop by merchantDetailId
      getShop: {
        url : "/shop/{merchantDetailId}",
        method : 'GET',
        requestData: {
            merchantDetailId（contain in url）
        }
        responseData: {
            ResultBody-jsonString
        }
      },
    }
    
    Admin {
        
        //get passed shops list
        getPassedShop:{
            url:"/merchantDetail/shop"
            method : "GET",
            response: {
                statuts : #String,
                data:{
                    shops: #List<String>
                }
            }
        }
        
        //get merchantDetail
        getPassedShop:{
            url:"/merchantDetail"
            method : "GET",
            response: {
                statuts : #String,
                data:{
                    merchantDetail: #MerchantDetail
                }
            }
        }
       
        // merchant register
        saveMerchant:{
            url: "/merchant/register",
            method: 'POST',
            response: {
                statuts : #String,
                data:{
                    merchant: #Merchant
                }
            }
        }
        
        // merchant login
        findMerchant:{
            url: "/merchant/login",
            method: 'POST',
            response: {
                statuts : #String,
                data:{
                    merchant: #Merchant
                }
            }
        }        
    }    
    
    Client {
        Login: {
            url : "/client/login",
            method : "POST",
            response : {
                status : #String
                data : {
                    client : #Object
                }
            }
        },
        getRecipeList {
            url : "/recipe/{shopId}"
            method : "GET",
            response: {
                statuts : #String,
                data:{
                    recipes: #Array
                }
            }
        }
        
        //添加订单（开启websocket）
        order:  {
            url ： "/order/"
            method : "post"
            requestBody : {
                order : {
                    shopId : string ,
                    address : string ,
                    phone : string ,
                    cost : double ,
                    client :{
                        id : string 
                    }
                    remark : string ,
                    status : integer
                    orderItems : [
                        orderItem : {recipeId : String,count : integer},
                        orderItem : {recipeId : String,count : integer},
                        orderItem : {recipeId : String,count : integer},
                    ]
                }
            }
        }
    }
