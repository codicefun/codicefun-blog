interface Article {
  title: string;
  summary: string;
  date: string;
}

const articles: Article[] = [
  {
    title: "Welcome to Codicefun Blog",
    summary:
      "Introducing our new editorial blog: what we write about and how the team publishes together.",
    date: "2026-08-16",
  },
  {
    title: "Getting Started with Next.js Full-Stack",
    summary:
      "A practical guide to building a full-stack blog with the App Router, Server Components and Server Actions.",
    date: "2026-08-12",
  },
  {
    title: "Designing an Editorial Workflow",
    summary:
      "How draft, review and publish stages shape the way authors and editors collaborate.",
    date: "2026-08-08",
  },
];

export default function Home() {
  return (
    <div className="flex flex-col gap-8">
      <section>
        <h1 className="text-3xl font-semibold">Latest Posts</h1>
        <p className="mt-2 text-zinc-600 dark:text-zinc-400">
          Stories and updates from the Codicefun team.
        </p>
      </section>
      <ul className="flex flex-col gap-4">
        {articles.map((article) => (
          <li
            key={article.title}
            className="rounded-lg border p-4"
          >
            <h2 className="text-xl font-medium">{article.title}</h2>
            <p className="mt-1 text-sm text-zinc-500">{article.date}</p>
            <p className="mt-2 text-zinc-600 dark:text-zinc-400">
              {article.summary}
            </p>
          </li>
        ))}
      </ul>
    </div>
  );
}
