The way terrier works is to read a hand-crafted configuration file and
route all output to preset locations. The IR experiment pipeline and
input and output data locations are specified in the file. On the
other hand, the entire configuration can be passed to it at the
command-line. It is clumsy, so is the configuration file itself. Using
[trecbox](https://github.com/sauparna/trecbox) much of this process
can be simplified.

Here is how to run Terrier on TREC disks 4 & 5.

###### Indexing
```
bin/trec_terrier.sh -i
		    -Dcollection.spec=CD45.733.s.docs
		    -Dterrier.index.path=x/X/index
		    -Dstopwords.filename=terrier733
		    -Dtermpipelines=Stop,SStemmer
		    -DTrecDocTags.doctag=DOC
		    -DTrecDocTags.idtag=DOCNO
		    -DTrecDocTags.process=
		    -DTrecDocTags.skip=		    
		    -DTrecDocTags.casesensitive=false
```

###### Retrieval
```
bin/trec_terrier.sh -r
		    -q
		    -c i
		    -Dterrier.index.path=X/Y/index
		    -Dtrec.topics=X/Y/query/351-400
		    -DTrecQueryTags.doctag=TOP
		    -DTrecQueryTags.idtag=NUM
		    -DTrecQueryTags.process=TOP,NUM,TITLE
		    -DTrecQueryTags.skip=DESC,NARR
		    -DTrecQueryTags.casesensitive=false
		    -Dstopwords.filename=X/Y/mist/terrier733
		    -Dtermpipelines=Stop,SStemmer
		    -Dtrec.model=TF_IDF
		    -Dquerying.postprocesses.controls=qe:QueryExpansion
		    -Dquerying.postprocesses.order=QueryExpansion
		    -Dtrec.qe.model=org.terrier.matching.models.queryexpansion.Bo1
		    -Dexpansion.terms=10
		    -Dexpansion.documents=3
		    -Dtrec.results=X/Y/runs
		    -Dtrec.results.file=T7.733.s.bm25.50.T.bo1
```

###### Data; input and output locations
```
X/
├── Y
│   ├── doc
│   │   └── CD45
│   ├── evals
│   │   ├── T7.733.s.tf_idf.50.T.bo1
│   ├── index
│   │   ├── CD45.733.s
│   │   ├── CD45.733.s.docs
│   │   └── CD45.733.s.log
│   ├── misc
│   │   └── terrier733
│   ├── qrel
│   │   └── 351-400.cd45-cr
│   ├── query
│   │   ├── 351-400
│   │   └── t7.50
│   └── runs
│       ├── T7.733.s.tf_idf.50.T.bo1
│       ├── T7.733.s.tf_idf.50.T.bo1.log
│       └── T7.733.s.tf_idf.50.T.bo1.settings
├── ...
```
