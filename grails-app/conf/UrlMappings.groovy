class UrlMappings {

	static mappings = {
		"/$controller/$action?/$number?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
