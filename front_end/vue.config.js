module.exports = {
	lintOnSave: false,
	// 修改的配置
	// cli3 代理是从指定的target后面开始匹配的，不是任意位置；配置pathRewrite可以做替换
	devServer: {
		proxy: {
			'/student_manager': {
				//你要跨域的域名(包含host、端口号,切记：一定要带上http头);
				//同一个域名只能设置一次跨域，否则重复报错！
				target: 'http://localhost:8081',
				ws: true,
				changeOrigin: true, //是否跨域，设置为true;(必须)
				pathRewrite: {
					// 这里会把当前域名下路径/florist开头的地方替换为http://127.0.0.1:8099/florist【这样就可以和服务器nginx保持一致的路径】
					"^/student_manager": "/student_manager", // 设置/FreightTransport路径重定向为根目录"/FreightTransport";
				}
			}
		}
	},
}