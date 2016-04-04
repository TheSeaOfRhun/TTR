**TTR** is a mod of Terrier (4.0) with a few additions and
accompanying documentation to help its users. The way Terrier works is
to read a hand-crafted configuration file and route all output to
predefined locations on disk. The IR experiment pipeline and input and
output data locations are specified in the file. On the other hand,
the entire configuration can be passed to it at the command-line. It
is clumsy, so is the configuration file itself. Here is a how to
quickly get started with running Terrier on TREC data. Copying and
pasting the command-line invocations to a shell script should do the
job. (All of this can be simplified using [trecbox][trb]).

[Documentation][ttrd]

###### Build

```
cd /x/TTR
ant
```

###### Indexing

```
bin/trec_terrier.sh -i                                       \
		    -Dcollection.spec=CD45.733.s.docs        \
		    -Dterrier.index.path=x/index/CD45.733.s  \
		    -Dstopwords.filename=x/misc/terrier733   \
		    -Dtermpipelines=Stop,SStemmer            \
		    -DTrecDocTags.doctag=DOC                 \
		    -DTrecDocTags.idtag=DOCNO                \
		    -DTrecDocTags.process=                   \
		    -DTrecDocTags.skip=		             \
		    -DTrecDocTags.casesensitive=false
```

###### Retrieval

```
bin/trec_terrier.sh -r                                                  \
		    -q                                                  \
		    -c i                                                \
		    -Dterrier.index.path=x/index/CD45.733.s             \
		    -Dtrec.topics=x/query/351-400                       \
		    -DTrecQueryTags.doctag=TOP                          \
		    -DTrecQueryTags.idtag=NUM                           \
		    -DTrecQueryTags.process=TOP,NUM,TITLE               \
		    -DTrecQueryTags.skip=DESC,NARR                      \
		    -DTrecQueryTags.casesensitive=false                 \
		    -Dstopwords.filename=x/misc/terrier733              \
		    -Dtermpipelines=Stop,SStemmer                       \
		    -Dtrec.model=TF_IDF                                 \
		    -Dquerying.postprocesses.controls=qe:QueryExpansion \
		    -Dquerying.postprocesses.order=QueryExpansion       \
		    -Dtrec.qe.model=org.terrier.matching.models.queryexpansion.Bo1 \
 		    -Dexpansion.terms=10                                \
		    -Dexpansion.documents=3                             \
		    -Dtrec.results=x/runs                               \
		    -Dtrec.results.file=T7.733.s.bm25.50.T.bo1
```

###### Locations of input and output data

```
/x
├── doc
│   └── CD45
│       ├── fbis -> /x/ir/docs/cd5/fbis
│       ├── fr94 -> /x/ir/docs/cd4/fr94
│       ├── ft -> /x/ir/docs/cd4/ft
│       └── latimes -> /x/ir/docs/cd5/latimes
├── evals
├── index
│   └── CD45.733.s
├── misc
│   └── terrier733
├── qrel
├── query
│   └── 351-400
└── runs
    └── T7.733.s.bm25.50.T.bo1

```

###### Note

A few points to note, and help clarify meaning:

- `/x` is a imaginary directory name, the layout does not have to be
  this.

- `/x/index/CD45.733.s` is an empty directory that was created before
  passing it to Terrier. The naming is arbitrary, but, if you use
  [trecbox][trb] to drive `TTR`, it will have a meaning.

- `/x/misc/terrier733` is a plain text file containing a list of stop
  words -- one on each line.

- `/x/query/351-400` is a plain text file containing formatted TREC
  queries. Each query is enclosed in a <TOP> tag and the text is
  placed within a <TEXT> tag. It was necessary to normalize the
  formatting because the older (early 1990's) TREC queries used a
  different structure. A [snippet of code][trbq] shows how to use
  _trecbox's_ query parser to convert them to this format.

  ```
  <TOP>
    <NUM>301</NUM>
    <TEXT>
     hello world
    </TEXT>
  <TOP>
  ```

[ttrd]: http://kak.tx0.org/IR/TTR/Doc/
[trb]:  http://kak.tx0.org/IR/trecbox/
[trbq]: http://kak.tx0.org/IR/trecbox/Doc/Query-Parser
