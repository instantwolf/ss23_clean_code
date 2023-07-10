This crawler runs on JDK15 and gradle with Groovy-DSL.

# Input Arguments

After application startup, you will be prompted to 

### 1. Enter your target domain(s)
### 2. Enter the target language for heading translation (in 2letter iso format)
### 3. Enter the desired crawl depth

# Crawler
The crawler features parallel crawling, but yet does not handle broken links nor highlight them in the results.

# Results
After execution, you can find the merged results (in case of parallel crawling) in the same folder as the exe file. 
The resultfile is named results.md and lists the crawled sites, links and headings in the format defined in the template file
that was provided to us.

Have fun crawling, cheers!

