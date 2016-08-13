cd ./doc
git init
git remote add javadoc https://weeryan17@github.com/weeryan17/Wee-Utilities.git
git fetch --depth=1 javadoc gh-pages
git add --all
git commit -m "javadoc update"
git merge --no-edit -s ours remotes/javadoc/gh-pages
git push javadoc master:gh-pages